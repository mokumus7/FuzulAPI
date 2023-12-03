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
public class ProductData {
    private String productPhotoUrl;
    private int id;
    private int propertyTypeEntityId;
    private String propertyTypeEntityValue;
    private int furnitureConditionEntityId;
    private String furnitureConditionEntityValue;
    private int numberOfRoomsEntityId;
    private String numberOfRoomsEntityValue;
    private int floorLevelEntityId;
    private String floorLevelEntityValue;
    private int buildingAgeEntityId;
    private String buildingAgeEntityValue;
    private String title;
    private String description;
    private double price;
    private int totalSquareFootage;


}
