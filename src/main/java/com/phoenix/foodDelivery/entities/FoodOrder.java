package com.phoenix.foodDelivery.entities;

import com.phoenix.foodDelivery.util.Status;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class FoodOrder extends BaseEntity{
    @Id
    private String id;
    private String status;

    @OneToMany(mappedBy ="foodOrder",cascade = CascadeType.ALL)
    private List<Food>foodList=new LinkedList();

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFood(Food foodList) {
        this.foodList.add(foodList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        Status stat = null;
        if (this.status.equalsIgnoreCase(Status.ACCEPTING.toString())) {
            stat = Status.ACCEPTING;
        } else if (this.status.equalsIgnoreCase(Status.COOKING.toString())) {
            stat = Status.COOKING;
        } else if (this.status.equalsIgnoreCase(Status.HANDOVER.toString())) {
            stat = Status.HANDOVER;
        } else if (this.status.equalsIgnoreCase(Status.PACKING.toString())) {
            stat = Status.PACKING;
        }else if (this.status.equalsIgnoreCase(Status.WaitingTillAccepting.toString())) {
            stat = Status.WaitingTillAccepting;
        }else if (this.status.equalsIgnoreCase(Status.COMPLETED.toString())) {
            stat = Status.COMPLETED;
        }


        return stat;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }
}
