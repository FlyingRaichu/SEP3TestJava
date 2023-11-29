package via.sep4.sep4test.services;
import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Favorite;
import via.sep4.protobuf.FavoriteServiceGrpc;
import via.sep4.protobuf.FavoritesRequest;
import via.sep4.protobuf.Item;
import via.sep4.sep4test.database.domain.DomainFavorite;
import via.sep4.sep4test.database.domain.DomainItem;
import via.sep4.sep4test.database.repository.FavoriteRepository;
import via.sep4.sep4test.database.repository.ItemRepository;
import via.sep4.sep4test.mappers.FavoriteMapper;
import via.sep4.sep4test.mappers.ItemMapper;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class FavoriteService extends FavoriteServiceGrpc.FavoriteServiceImplBase
{
    private FavoriteRepository favRepository;
    private ItemRepository itemRepository;
    private final FavoriteMapper mapper = FavoriteMapper.INSTANCE;
    private final ItemMapper itemMapper = ItemMapper.INSTANCE;

    public FavoriteService(FavoriteRepository favRepository, ItemRepository itemRepository)
    {
        this.favRepository = favRepository;
        this.itemRepository = itemRepository;
    }

    @Override public void getFavorite(Favorite favorite, StreamObserver<Favorite> responseObserver)
    {
        DomainFavorite fav = favRepository.findByUserIdAndItemId(favorite.getUserId(),favorite.getItemId());
        Favorite protoFav = mapper.toProto(fav);

        responseObserver.onNext(protoFav);
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

    @Override public void getUserFavorites(FavoritesRequest request, StreamObserver<Item> responseObserver) {
        List<DomainFavorite> userFavorites = favRepository.findAllByUserId(request.getUserId());
        List<Integer> ids = new ArrayList<>();

        //grabs ids of each item from favorites
        for (DomainFavorite d : userFavorites)
        {
            ids.add(d.getItemId());
        }

        //get the associated item and convert to proto obj
        List<DomainItem> items = itemRepository.findAllByIdIn(ids);
        for (DomainItem i : items)
        {
            responseObserver.onNext(itemMapper.toProto(i));
        }

        responseObserver.onCompleted();
    }
}
