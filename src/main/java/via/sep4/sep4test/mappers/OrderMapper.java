package via.sep4.sep4test.mappers;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import via.sep4.protobuf.Order;
import via.sep4.sep4test.database.domain.DomainOrder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Mapper(uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toProto(DomainOrder domainOrder);
    DomainOrder toEntity(Order order);

    default Date stringToDate(String dateString){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format", e);
        }
    }

    default String dateToString(Date date) {
        return DateTimeFormatter.ISO_DATE.format(date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
    }
}
