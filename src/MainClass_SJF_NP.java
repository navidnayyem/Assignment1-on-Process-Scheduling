import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainClass_SJF_NP {
	public static void main(String args[]) {
		int i,j,k, current_time = 0, total = 0;
		float avgwt = 0, avgtat = 0;
		System.out.println("Shortest Job First - NON PREEMPTIVE");
		System.out.println("-----------------------------------\n");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Mohammad Navid\\Desktop\\input.txt")); // txt file path (location)
			String line = reader.readLine(); // reads first line from txt file
			int numberofprocesses = Integer.parseInt(line); // Parses the line to obtain an integer value from string (method)
			System.out.println("Number of Processes: " + numberofprocesses); // prints the number of processes from the file

			int id[] = new int[numberofprocesses]; // declaring process id array
			int duration[] = new int[numberofprocesses]; // declaring duration time array
			int arrival_time[] = new int[numberofprocesses]; // declaring arrival time array
			int is_completed[] = new int[numberofprocesses]; //complete process

			for (i = 0; i < numberofprocesses; i++) { // for loop to read processid, durationtime and arrival time
				line = reader.readLine(); // read second line from file
				String[] listofProcess = line.split("\\s+"); // split listofProcess into fields
				id[i] = Integer.parseInt(listofProcess[0]); // process id field
				duration[i] = Integer.parseInt(listofProcess[1]); // duration time field
				arrival_time[i] = Integer.parseInt(listofProcess[2]); // arrival time field
				is_completed[i] = 0; //process is completed
			}

			int end_time[] = new int[numberofprocesses]; // declaring end time array
			int turnaround_time[] = new int[numberofprocesses]; // declaring turnaround time array
			int waiting_time[] = new int[numberofprocesses]; // declaring waiting time array

			while (true) {
				int nop = numberofprocesses;
			    int min = 99999;
				if (total == numberofprocesses) // total no of process = if completed then process loop will be terminated
					break;

				for (j = 0; j < numberofprocesses; j++) {

					if ((arrival_time[j] <= current_time) && (is_completed[j] == 0) && (duration[j] < min)) {
						min = duration[j];
						nop = j;
					}
				}

				if (nop == numberofprocesses) {
					current_time++;
				} else {
					end_time[nop] = current_time + duration[nop];
					current_time = current_time + duration[nop];
					turnaround_time[nop] = end_time[nop] - arrival_time[nop];
					waiting_time[nop] = turnaround_time[nop] - duration[nop];
					is_completed[nop] = 1;
					total++;
				}
			}

			System.out.println("\nPid  DurationTime  ArrivalTime  EndTime  WaitingTime  TurnaroundTime");
			for (k = 0; k < numberofprocesses; k++) {
				avgwt = avgwt + waiting_time[k];
				avgtat = avgtat + turnaround_time[k];
				System.out.println(id[k] + "  \t " + duration[k] + "\t\t" + arrival_time[k] + "\t  " + end_time[k] + "\t     " + waiting_time[k]
						+ "\t\t    " + turnaround_time[k]);
			}
			System.out.println("\nAverage Waiting Time   : " + (avgwt / numberofprocesses)); // printing average waiting time.
			System.out.println("Average Turnaround Time: " + (avgtat / numberofprocesses)); // printing average turnaround time.
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		// Process function = new Process(0, 0, 0);
		// function.run();
	}
}