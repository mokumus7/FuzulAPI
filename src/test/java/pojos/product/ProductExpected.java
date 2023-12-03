package pojos.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductExpected {

    private int pageNumber;
    private int pageSize;
    private int apiResultType;
    private Object message;
    private ProductData productData;

}
