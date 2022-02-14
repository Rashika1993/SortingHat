import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class SortingHat {

    enum FoodPreferenceType{
        V,NV
    }
    enum ClassType{
        A,B
    }
    int max;
    List<Integer> rollNoList =new ArrayList<Integer>();
    List<Integer> nonAllocatedStudentList =new ArrayList<Integer>();
    PrintSortingHat printSortingHat;
    Map<ClassType,Map<FoodPreferenceType,Queue<Integer>>> boardingHouseTypeMap;
    public SortingHat(int max){
        this.max=max;
        this.printSortingHat=new PrintSortingHat();
        boardingHouseTypeMap=new HashMap<ClassType, Map<FoodPreferenceType, Queue<Integer>>>();
        for(ClassType classType:ClassType.values()){
            boardingHouseTypeMap.put(classType,new HashMap<FoodPreferenceType, Queue<Integer>>());
            Map<FoodPreferenceType,Queue<Integer>> foodPreferenceTypeQueueMap=null;
            for(FoodPreferenceType foodPreferenceType:FoodPreferenceType.values()){
                foodPreferenceTypeQueueMap=new HashMap<FoodPreferenceType, Queue<Integer>>();
                foodPreferenceTypeQueueMap.put(foodPreferenceType,new LinkedBlockingQueue<Integer>());
                boardingHouseTypeMap.get(classType).putAll(foodPreferenceTypeQueueMap);
            }
        }
    }
    private boolean registerUser(int rollNo,ClassType classType,FoodPreferenceType foodPreferenceType){
        if(rollNoList.contains(rollNo))
            return false;
        if(rollNo<=max){
            if(boardingHouseTypeMap.get(classType)==null||boardingHouseTypeMap.get(classType).get(foodPreferenceType)==null){
               return false;
            }
         boardingHouseTypeMap.get(classType).get(foodPreferenceType).add(rollNo);
         rollNoList.add(rollNo);
         return true;
        }
        else {
            nonAllocatedStudentList.add(rollNo);
            return false;
        }
    }

    public static void main(String[] args) {
        SortingHat sortingHat=new SortingHat(12);
        sortingHat.registerUser(1 ,ClassType.B, FoodPreferenceType.V);
        sortingHat.registerUser(2 ,ClassType.A, FoodPreferenceType.V);
        sortingHat.registerUser(3 ,ClassType.A, FoodPreferenceType.V);
        sortingHat.registerUser(4 ,ClassType.B, FoodPreferenceType.NV);
        sortingHat.registerUser(5 ,ClassType.B, FoodPreferenceType.V);
        sortingHat.registerUser(6 ,ClassType.A, FoodPreferenceType.NV);
        sortingHat.registerUser(7 ,ClassType.A, FoodPreferenceType.V);
        sortingHat.registerUser(8 ,ClassType.A, FoodPreferenceType.NV);
        sortingHat.registerUser(9 ,ClassType.B, FoodPreferenceType.NV);
        sortingHat.registerUser(10 ,ClassType.B, FoodPreferenceType.V);
        sortingHat.registerUser(11 ,ClassType.A, FoodPreferenceType.NV);
        sortingHat.registerUser(12 ,ClassType.B, FoodPreferenceType.NV);
        sortingHat.printAllocatedStudents();
        sortingHat.printNonAllocatedStudents();

    }
    private void printAllocatedStudents(){
        printSortingHat.printAllocatedStudents(boardingHouseTypeMap);
    }
    private void printNonAllocatedStudents(){
        printSortingHat.printNonAllocatedStudents(nonAllocatedStudentList);
    }



}
class PrintSortingHat{
    public void printAllocatedStudents(Map<SortingHat.ClassType,Map<SortingHat.FoodPreferenceType,Queue<Integer>>> boardingHouseTypeMap){
        for(Map.Entry<SortingHat.ClassType, Map<SortingHat.FoodPreferenceType, Queue<Integer>>> foodPreferenceTypeMap:
                boardingHouseTypeMap.entrySet()){
            for(Map.Entry<SortingHat.FoodPreferenceType, Queue<Integer>> foodPreferenceTypeQueueEntry:foodPreferenceTypeMap.getValue().entrySet()){
                System.out.println(foodPreferenceTypeMap.getKey()+":"+foodPreferenceTypeQueueEntry.getKey().toString()+"["+ foodPreferenceTypeQueueEntry.getValue()+"]");
            }
        }
    }
    public void printNonAllocatedStudents(List<Integer> nonAllocatedStudentList){
        System.out.print("NA:[");
        for(Integer nonAllocatedStudent: nonAllocatedStudentList){
            System.out.print(nonAllocatedStudent);
        }
        System.out.print("]");

    }
}
