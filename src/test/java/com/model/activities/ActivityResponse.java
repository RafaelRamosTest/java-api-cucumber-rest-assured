package com.model.activities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // Permite criar o objeto passando os dados direto no construtor
@NoArgsConstructor  // Mantém o construtor vazio padrão para o Jackson desserializar
public class ActivityResponse {
    private int id;
    private String title;
    private String dueDate;
    private boolean completed;
}
