import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainClass_FCFS {
	public static void main(String[] args) throws FileNotFoundException {
		int i, j, k, l, m, temp;
		float avgwt = 0, avgtat = 0;
		System.out.println("First Come First Serve");
		System.out.println("----------------------\n");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("C:\\Users\\Mohammad Navid\\Desktop\\input.txt")); // txt file path (location)
			String line = reader.readLine(); // reads first line from txt file
			int numberofprocesses = Integer.parseInt(line); // Parses the line to obtain an integer value from string (method)
			System.out.println("Number of Processes: " + numberofprocesses); // prints the number of processes from the file

			int id[] = new int[numberofprocesses]; // declaring process id array
			int duration[] = new int[numberofprocesses]; // declaring duration time array
			int arrival_time[] = new int[numberofprocesses]; // declaring arrival time array

			for (i = 0; i < numberofprocesses; i++) { // for loop to read processid, durationtime and arrival time
				line = reader.readLine(); // read second line from file
				String[] listofProcess = line.split("\\s+"); // split listofProcess into fields
				id[i] = Integer.parseInt(listofProcess[0]); // process id field
				duration[i] = Integer.parseInt(listofProcess[1]); // duration time field
				arrival_time[i] = Integer.parseInt(listofProcess[2]); // arrival time field
			}

			// sorting list of process according to arrival times
			for (j = 0; j < numberofprocesses; j++) {
				for (k = 0; k < numberofprocesses - (j + 1); k++) {
					if (arrival_time[k] > arrival_time[k + 1]) {
						temp = arrival_time[k];
						arrival_time[k] = arrival_time[k + 1];
						arrival_time[k + 1] = temp;
						temp = duration[k];
						duration[k] = duration[k + 1];
						duration[k + 1] = temp;
						temp = id[k];
						id[k] = id[k + 1];
						id[k + 1] = temp;
					}
				}
			}

			int end_time[] = new int[numberofprocesses]; // declaring end time array
			int waiting_time[] = new int[numberofprocesses]; // declaring waiting time array
			int turnaround_time[] = new int[numberofprocesses]; // declaring turnaround time array

			// finding end time
			for (l = 0; l < numberofprocesses; l++) {
				if (l == 0) {
					end_time[l] = arrival_time[l] + duration[l];
				} else {
					if (arrival_time[l] > end_time[l - 1]) {
						end_time[l] = arrival_time[l] + duration[l];
					} else
						end_time[l] = end_time[l - 1] + duration[l];
				}
				turnaround_time[l] = end_time[l] - arrival_time[l]; // turnaround time = end time - arrival time
				waiting_time[l] = turnaround_time[l] - duration[l]; // waiting time = turnaround time - burst time
				avgwt = avgwt + waiting_time[l]; // total waiting time
				avgtat = avgtat + turnaround_time[l]; // total turnaround time
			}

			System.out.println("\nPid  DurationTime  ArrivalTime  EndTime  WaitingTime  TurnaroundTime");
			for (m = 0; m < numberofprocesses; m++) {
				System.out.println(id[m] + "  \t " + duration[m] + "\t\t" + arrival_time[m] + "\t  " + end_time[m] + "\t     " + waiting_time[m] + "\t\t    " + turnaround_time[m]);
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