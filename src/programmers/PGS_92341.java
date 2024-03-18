package programmers;

import java.util.*;

class PGS_92341 {
    public static void main(String[] args) {
        System.out.println("asd");
    }
}

//class Solution {
//    static class ParkingLot {
//        int defaultTime;
//        int defaultFee;
//        int unitTime;
//        int unitFee;
//
//        public ParkingLot(int defaultTime, int defaultFee, int unitTime, int unitFee) {
//            this.defaultTime = defaultTime;
//            this.defaultFee = defaultFee;
//            this.unitTime = unitTime;
//            this.unitFee = unitFee;
//        }
//
//        public int calculateFee(int totalTime) {
//            if (totalTime <= defaultTime) {
//                return defaultFee;
//            } else {
//                return defaultFee + (int) (Math.ceil((double) (totalTime - defaultTime) / unitTime)) * unitFee;
//            }
//        }
//    }
//
//    static class Car {
//        String carNumber;
//        String inTime;
//        String outTime = "23:59";
//        int totalTime;
//
//        public Car(String carNumber, String inTime) {
//            this.carNumber = carNumber;
//            this.inTime = inTime;
//            calculateTime();
//        }
//
//        public void setOutTime(String outTime) {
//            this.outTime = outTime;
//            calculateTime();
//        }
//
//        private void calculateTime() {
//            int inTimeHour = Integer.parseInt(inTime.split(":")[0]);
//            int inTimeMin = Integer.parseInt(inTime.split(":")[1]);
//
//            int outTimeHour = Integer.parseInt(outTime.split(":")[0]);
//            int outTimeMin = Integer.parseInt(outTime.split(":")[1]);
//
//            int calHour = outTimeHour - inTimeHour;
//            int calMin = outTimeMin - inTimeMin;
//
//            this.totalTime = calHour * 60 + calMin;
//        }
//    }
//
//    public int[] solution(int[] fees, String[] records) {
//        ParkingLot parkingLot = new ParkingLot(fees[0], fees[1], fees[2], fees[3]);
//        Map<String, Car> carMap = new HashMap<>();
//        Map<String, Integer> feeMap = new TreeMap<>();
//
//        for (String record : records) {
//            StringTokenizer st = new StringTokenizer(record);
//            String time = st.nextToken();
//            String carNumber = st.nextToken();
//            String direction = st.nextToken();
//
//            carMap.putIfAbsent(carNumber, new Car(carNumber, "00:00"));
//            Car car = carMap.get(carNumber);
//
//            if (direction.equals("IN")) {
//                car.setOutTime("23:59"); // 기본 출차 시간 설정
//                car.inTime = time;
//            } else {
//                car.setOutTime(time);
//                feeMap.put(carNumber, feeMap.getOrDefault(carNumber, 0) + parkingLot.calculateFee(car.totalTime));
//            }
//        }
//
//        // 출차되지 않은 차량 처리
//        for (Map.Entry<String, Car> entry : carMap.entrySet()) {
//            String carNumber = entry.getKey();
//            Car car = entry.getValue();
//            feeMap.put(carNumber, feeMap.getOrDefault(carNumber, 0) + parkingLot.calculateFee(car.totalTime));
//        }
//
//        return feeMap.values().stream().mapToInt(Integer::intValue).toArray();
//    }
//
//}