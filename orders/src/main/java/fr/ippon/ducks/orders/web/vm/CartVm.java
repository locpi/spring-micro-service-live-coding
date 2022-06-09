package fr.ippon.ducks.orders.web.vm;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartVm {


    private String id;

    private String clientName;

    private List<String> references = new ArrayList<>();


}
