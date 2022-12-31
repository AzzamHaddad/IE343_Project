import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
        public static void main(String[] args) throws IOException{
                List<List<String>> list = readValues();
                System.out.println(list.get(0));
		List<Integer> valueList = new ArrayList<Integer>();
		List<Integer> weightList = new ArrayList<Integer>();
		//Added code
		List<Track> tracks = new ArrayList<>();
		for(int i=1;i<list.size();i++) {
			valueList.add(Integer.parseInt(list.get(i).get(4))); 
			weightList.add(Integer.parseInt(list.get(i).get(5)));
			Track track = new Track(Integer.parseInt(list.get(i).get(0)), Integer.parseInt(list.get(i).get(4)));
			tracks.add(track);
			}
		List<List<String>> list1 = readSequential();

		BottomUp solution = new BottomUp();

		solution.solve(valueList.size(), valueList,weightList,1800000/10);
		// 1800000 milliseconds

	    // Find Most valuable songs id:
		tracks.sort((e1,e2) -> e1.getIndividualValue()  - e2.getIndividualValue());
		int startNode = tracks.get(0).getId();
		int endNode = tracks.get(1).getId();
			List<List<Double>> l1 = new ArrayList<>();
		for (int i=1;i<list1.size();i++){
			ArrayList<Double> row = new ArrayList<>();
			for (int j=1;j<list1.get(0).size();j++){
				row.add(Double.parseDouble(list1.get(i).get(j)));
			}
			l1.add(row);

		}
		double [][] data = new double[l1.size()][l1.size()];
		for(int i =0; i < l1.size();i++){
			for(int j = 0; j < l1.get(i).size();j++){
				data[i][j] = l1.get(i).get(j);
			}
		}





		Dijkstra dj = new Dijkstra(data);
			System.out.println(dj.solve(startNode,endNode));
			dj.printPath(startNode,endNode);

//		System.out.println(sequential_data.get(0).get(1));
		
		

        }
        public static List<List<String>> readValues() throws IOException { 
                try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_value_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}
			
			//printing the fetched data
			for(List<String> list : data)
			{
				for(String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			br.close();
                        return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
                        List< List<String> > data = new ArrayList<>();//list of lists to store data
                        return data;
		}
                
        }
	public static List<List<String>> readSequential() throws IOException { 
                try
		{
			List< List<String> > data = new ArrayList<>();//list of lists to store data
			String file = "term_project_sequential_data.csv";//file path
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Reading until we run out of lines
			String line = br.readLine();
			while(line != null)
			{
				List<String> lineData = Arrays.asList(line.split(","));//splitting lines
				data.add(lineData);
				line = br.readLine();
			}
			
			//printing the fetched data
			for(List<String> list : data)
			{
				for(String str : list)
					System.out.print(str + " ");
				System.out.println();
			}
			br.close();
                        return data;
		}
		catch(Exception e)
		{
			System.out.print(e);
                        List< List<String> > data = new ArrayList<>();//list of lists to store data
                        return data;
		}
                
        }
}
