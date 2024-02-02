package com.phoenix.foodDelivery.util;

import com.phoenix.foodDelivery.dto.Food;

public class FoodInterpreter {

    private Food f;

   public FoodInterpreter(Food food){
        String[] commandList= food.getCommands().split(",");
        this.f=food;
        for (String command:commandList) {
            String[] ex=command.split(" ");
            String terminalEx=ex[1];
            String noneTerminalEx=ex[0];

            Keywords kw=null;

            if(Keywords.EXTRA.toString().equalsIgnoreCase(noneTerminalEx)){
                kw=Keywords.EXTRA;
            }else if(Keywords.LESS.toString().equalsIgnoreCase(noneTerminalEx)){
                kw=Keywords.LESS;
            }else if (Keywords.NO.toString().equalsIgnoreCase(noneTerminalEx)){
                kw=Keywords.NO;
            }

            f=new ToppingExpression(new TerminalExpression(terminalEx),f,kw).interpret();
        }
    }

    public Food getFood(){
        return f;
    }
}
