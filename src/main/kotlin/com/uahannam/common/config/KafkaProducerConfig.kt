package com.uahannam.common.config

import com.uahannam.order.adapter.out.kafka.produce.dto.SaveOrderKafkaDto
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonSerializer

@EnableKafka
@Configuration
class KafkaProducerConfig {

    @Bean
    fun saveOrderServiceDataProducerFactory() : DefaultKafkaProducerFactory<String, SaveOrderKafkaDto> {
        return DefaultKafkaProducerFactory(saveOrderServiceProducerConfig())
    }

    @Bean
    fun saveOrderServiceProducerConfig() = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to "",
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class
    )

    @Bean
    fun saveOrderKafkaTemplate() : KafkaTemplate<String, SaveOrderKafkaDto> {
        return KafkaTemplate(saveOrderServiceDataProducerFactory())
    }
}