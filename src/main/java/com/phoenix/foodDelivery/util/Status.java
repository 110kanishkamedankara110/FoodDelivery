package com.phoenix.foodDelivery.util;

public enum Status {
    ACCEPTING{
        @Override
        public String toString() {
            return "Accepting";
        }
    },COOKING{
        @Override
        public String toString() {
            return "Cooking";
        }
    },PACKING{
        @Override
        public String toString() {
            return "Packing";
        }
    },HANDOVER{
        @Override
        public String toString() {
            return "HandOver";
        }
    },WaitingTillAccepting{
        @Override
        public String toString() {
            return "Waiting Till Accepting";
        }
    },COMPLETED{
        @Override
        public String toString() {
            return "Completed";
        }
    };
}
