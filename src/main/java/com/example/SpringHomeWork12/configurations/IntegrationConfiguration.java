package com.example.SpringHomeWork12.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.io.File;

@Configuration
public class IntegrationConfiguration {

    /**
     * Первый канал от входных данных до transformer
     * @return
     */
    @Bean
    public MessageChannel messageChannelInput(){
        return new DirectChannel();
    }

    /**
     * Второй канал - это канал передачи данных от transformer до модуля который будет сохранять данные в файл
     * @return
     */
    @Bean
    public MessageChannel messageChannelFileWriter() {
        return new DirectChannel();
    }

    /**
     * Третий канал. Добавляем сам трансформер
     * myTransformer - создаем маршрутизатор
     * inputChannel - указываем, кто является входным файлом для данного блока
     * outputChannel - указываем, кто является выходным файлом
     */
    @Bean
    @Transformer(inputChannel = "messageChannelInput", outputChannel = "messageChannelFileWriter")
    public GenericTransformer<String, String> myTransformer() {
        return text -> {
            return text.toUpperCase();
        };
    }

    /**
     * Четвертый канал. Выход из нашей интеграции
     */
    @Bean
    @ServiceActivator(inputChannel = "messageChannelFileWriter")
    public FileWritingMessageHandler myFileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("/Users/zhangeldy/Documents/Фреймворк_Spring/Projects/SpringHomeWork12/src/main/java"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
