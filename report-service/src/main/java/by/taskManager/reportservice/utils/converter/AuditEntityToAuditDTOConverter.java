package by.taskManager.reportservice.utils.converter;


import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditDTOConverter implements Converter<AuditEntity,AuditDTO> {

    @Override
    public AuditDTO convert(AuditEntity source) {
        AuditDTO audit = new AuditDTO();
        user.setFio(source.getFio());
        user.setMail(source.getMail());
        user.setRole(source.getRole());
        user.setStatus(source.getStatus());
        user.setUuid(source.getUuid());
        user.setDtCreate(source.getDtCreate());
        user.setDtUpdate(source.getDtUpdate());
        return audit;
    }
}
