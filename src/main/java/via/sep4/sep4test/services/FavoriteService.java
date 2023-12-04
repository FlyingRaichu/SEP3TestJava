package via.sep4.sep4test.services;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Favorite;
import via.sep4.protobuf.FavoriteServiceGrpc;
import via.sep4.sep4test.database.domain.DomainFavorite;
import via.sep4.sep4test.database.repository.FavoriteRepository;
import via.sep4.sep4test.mappers.FavoriteMapper;

import java.util.List;

@GrpcService
public class FavoriteService extends FavoriteServiceGrpc.FavoriteServiceImplBase
{
    private FavoriteRepository favRepository;
    private final FavoriteMapper mapper = FavoriteMapper.INSTANCE;

    public FavoriteService(FavoriteRepository favRepository)
    {
        this.favRepository = favRepository;
    }

    @Override public void getFavorite(Favorite favorite, StreamObserver<Favorite> responseObserver)
    {
        DomainFavorite fav = favRepository.findByUserIdAndItemId(favorite.getUserId(),favorite.getItemId());
        Favorite protoFav = mapper.toProto(fav);

        responseObserver.onNext(protoFav);
        responseObserver.onCompleted();
    }

    @Override public void getAllFavorites(Empty request, StreamObserver<Favorite> responseObserver)
    {
        List<DomainFavorite> favs = favRepository.findAll();

        for (DomainFavorite fav : favs)
        {
            Favorite protoFav = mapper.toProto(fav);
            responseObserver.onNext(protoFav);
        }

        responseObserver.onCompleted();
    }

    @Override public void addFavorite(Favorite favorite, StreamObserver<Empty> responseObserver)
    {
        DomainFavorite fav = mapper.toEntity(favorite);
        favRepository.save(fav);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override public void deleteFavorite(Favorite favorite, StreamObserver<Empty> responseObserver)
    {
        DomainFavorite fav = mapper.toEntity(favorite);
        favRepository.delete(fav);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
