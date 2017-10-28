package org.mroczkarobert.creditsuisse.util;

import java.io.IOException;

import org.mroczkarobert.creditsuisse.type.ProductType;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ProductTypeDeserializer extends StdDeserializer<ProductType> {

	private static final long serialVersionUID = 8324660911935917186L;

	protected ProductTypeDeserializer() {
        super(ProductType.class);
    }

    @Override
    public ProductType deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
    	String value = parser.readValueAs(String.class);

    	for (ProductType productType : ProductType.values()) {
    		if (value.endsWith(productType.getJsonValue())) {
    			return productType;
    		}
    	}
    	
		return null;
    }
}