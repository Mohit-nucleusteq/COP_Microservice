package com.order.service.dbo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "orders")
public class Order {

	@Transient
	public static final String SEQUENCE_NAME = "order_sequence";

    @Id
    private Long id;
    private Long buyerId;
    private List<String> products;

}
