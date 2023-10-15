package br.com.nickolasmartins.todolist.task;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
     
     @Id
     @GeneratedValue(generator = "UUID")
     private UUID id; //id da tarefa
     private String description; //descrição da tarefa

     @Column(length = 50)
     private String title; //título da tarefa
     private LocalDateTime startAt; //quando a tarefa foi criada
     private LocalDateTime endAt; //término da tarefa
     private String priority; //prioridade da tarefa

     private UUID idUser; //id do usuário relacionado a tarefa

     @CreationTimestamp
     private LocalDateTime createdAt; //Quando a tarefa foi criada

     public void setTitle(String title) throws Exception{
          if(title.length() > 50){
               throw new Exception("O campo title deve conter no máximo 50 caracteres");
          }
          this.title = title;
     }
}
