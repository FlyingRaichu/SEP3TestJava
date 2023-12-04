package via.sep4.sep4test.services;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import via.sep4.protobuf.Tag;
import via.sep4.protobuf.TagServiceGrpc;
import via.sep4.sep4test.database.domain.DomainTag;
import via.sep4.sep4test.database.repository.TagRepository;
import via.sep4.sep4test.mappers.TagMapper;

import java.util.List;

@GrpcService
public class TagService extends TagServiceGrpc.TagServiceImplBase {
    private TagRepository tagRepository;
    private TagMapper mapper = TagMapper.INSTANCE;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void getAllTags(Empty request, StreamObserver<Tag> responseObserver) {
        List<Tag> tags = mapper.toProtoList(tagRepository.findAll());

        tags.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void addTag(Tag request, StreamObserver<Empty> responseObserver) {
        DomainTag domainTag = mapper.toEntity(request);

        tagRepository.save(domainTag);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateTag(Tag request, StreamObserver<Empty> responseObserver) {
        Int32Value id = Int32Value.of(request.getId());
        DomainTag domainTag = mapper.toEntity(request);
        DomainTag tagToDelete = tagRepository.findById(id.getValue()).orElseThrow();

        tagRepository.delete(tagToDelete);
        tagRepository.save(domainTag);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTag(Tag request, StreamObserver<Empty> responseObserver) {
        DomainTag domainTag = mapper.toEntity(request);

        tagRepository.delete(domainTag);

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}