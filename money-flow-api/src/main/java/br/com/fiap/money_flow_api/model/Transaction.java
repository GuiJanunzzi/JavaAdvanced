package br.com.fiap.money_flow_api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Transaction {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo obrigatório")
    @Size(min = 5, max = 255, message = "deve ter pelo menos 4 caracteres")
    private String description;

    @Positive(message = "deve ser positivo")
    private BigDecimal amount;

    @PastOrPresent(message = "deve ser no passado ou presente")
    private LocalDate date;

    @NotNull(message = "campo obrigatório")
    @Enumerated(EnumType.STRING)//Como será salvo
    private TransactionType type;

    @NotNull(message = "campo obrgatório")
    //cardinalidade
    //M : 1
    @ManyToOne
    private Category category;
}
