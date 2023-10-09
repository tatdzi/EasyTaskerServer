package by.taskManager.reportservice.core.converter;

import by.TaskManeger.utils.dto.ReportParamAudit;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class ToDTOsConverter<IN, OUT> implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Set.of(new ConvertiblePair(Map.class, ReportParamAudit.class));

    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Class<?> expectedSourceClass = sourceType.getType();
        Class<?> expectedTargetClass = targetType.getType();
        ReportParamAudit res = new ReportParamAudit();
        if (expectedSourceClass.equals(Map.class) && expectedTargetClass.equals(ReportParamAudit.class)) {
            Map<String, String> params = (Map<String, String>) source;
            if (params.containsKey("user")) {
                UUID id = UUID.fromString(params.get("user"));
                res.setUser(id);
            }
            LocalDate from = LocalDate.parse(params.get("from"));
            LocalDate to = LocalDate.parse(params.get("to"));
            res.setFrom(from);
            res.setTo(to);
            /*
            List<?> fromNums = (List<?>) params.get("from");
            if (fromNums != null){
                LocalDate from = LocalDate.of(
                        (int) fromNums.get(0),
                        (int) fromNums.get(1),
                        (int) fromNums.get(2)
                );
                LocalDateTime f = LocalDateTime.of(
                        (int) fromNums.get(0),
                        (int) fromNums.get(1),
                        (int) fromNums.get(2),
                        (int) fromNums.get(3),
                        (int) fromNums.get(4)
                );
                res.setFrom(f);
            }
            List<?> toNums = (List<?>) params.get("to");
            if (toNums != null){
                LocalDate to = LocalDate.of(
                        (int) toNums.get(0),
                        (int) toNums.get(1),
                        (int) toNums.get(2)
                );
                LocalDateTime t = LocalDateTime.of(
                        (int) fromNums.get(0),
                        (int) fromNums.get(1),
                        (int) fromNums.get(2),
                        (int) fromNums.get(3),
                        (int) fromNums.get(4)
                );
                res.setTo(t);
             */
        }
        return res;
    }
}
