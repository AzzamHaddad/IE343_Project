import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

public class BottomUp {


    public void solve(int n, List<Integer> v, List<Integer> w, Integer W){


        System.out.println("Initial Memory footprint");
        memoryUsage();

        Integer[][] keep = new Integer[v.size()+1][W+1];
        Integer [][] V = new Integer[w.size()+1][W+1];
        Integer solution[] = new Integer[n+1];

        List<Integer> duration = new ArrayList<>();
        for(Integer win : w){
            duration.add((win/10));
        }

        for(int i = 0; i < w.size();i++) {
            for (int m = 0; m <= W; m++) {
                V[i][m] = 0;
            }
        }

        for(int i = 1; i <= w.size();i++){
            for(int j = 0; j <= W;j++){
                Integer weight = duration.get(i-1);
                if(weight <= j){
                    V[i][j] = Math.max(v.get(i-1)+V[i-1][j-weight], V[i-1][j]);
                    keep[i][j] = 1;
                }else{
                    V[i][j] = V[i-1][j];
                    keep[i][j] =0;
                }
            }
        }

        int K = W;
        int totalDuration =0;
        for(int i = w.size();i > 0 ; i--){
            if(keep[i][K] == 1){
                solution[i] = 1;
                K -= duration.get(i-1);
                System.out.println(i-1+ " --> Song included");
                totalDuration = totalDuration + duration.get(i-1);
            }else{
                solution[i] = 0;
            }
        }

        System.out.println("After execution memory usage");
        memoryUsage();
        System.out.println("Total duration :" + totalDuration*10);
        System.out.println("Obj " + V[w.size()][W]);


    }
    
    private  void memoryUsage(){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        System.out.println(String.format("Initial memory: %.2f GB",(double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824));

        System.out.println(String.format("Used heap memory: %.2f GB",

        (double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824));

        System.out.println(String.format("Max heap memory: %.2f GB",

        (double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824));

        System.out.println(String.format("Committed memory: %.2f GB",

        (double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824));
    }
}
