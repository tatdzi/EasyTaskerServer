package by.taskManager.auditservice.converter;


import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditDTOConverter implements Converter<AuditEntity, AuditDTO> {

    @Override
    public AuditDTO convert(AuditEntity source) {
        AuditDTO audit = new AuditDTO();
        audit.setUuid(source.getUuid());
        audit.setId(source.getId());
        audit.setType(source.getType());
        audit.setText(source.getText());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUuid(source.getUuid());
        tokenDTO.setMail(source.getUserMail());
        tokenDTO.setFio(source.getUserFio());
        tokenDTO.setRole(source.getUserRole());
        audit.setUser(tokenDTO);
        audit.setDtCreate(source.getDtCreate());
        return audit;
    }
}
