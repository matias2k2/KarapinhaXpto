package tinario9954.gmail.com.KarapinhaApi.Services.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tinario9954.gmail.com.KarapinhaApi.DTOS.UserInsertDTO;
import tinario9954.gmail.com.KarapinhaApi.Repository.UserRepository;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        // Exemplo de validação: verificar se o e-mail já está em uso
        List<FieldMessage> list = new ArrayList<>();

        if (userRepository.existsByEmail(dto.getEmail())) {
            list.add(new FieldMessage("email", "Email já está em uso"));
        }

        // Adiciona os erros personalizados na resposta
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
