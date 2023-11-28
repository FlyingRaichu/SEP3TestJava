package via.sep4.sep4test.services;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Favorite;
import via.sep4.protobuf.FavoriteServiceGrpc;
import via.sep4.sep4test.database.domain.DomainFavorite;
import via.sep4.sep4test.database.repository.FavoriteRepository;
import via.sep4.sep4test.mappers.FavoriteMapper;

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
        DomainFavorite fav = favRepository.findByAllFields(favorite.getUserId(),favorite.getItemId());
        Favorite protoFav = mapper.toProto(fav);

        responseObserver.onNext(protoFav);
        responseObserver.onCompleted();
    }
}
