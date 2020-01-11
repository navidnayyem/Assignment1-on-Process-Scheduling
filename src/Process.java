
public class Process {
	int current_time = 0;
	int ID, duration, arrival_time, start_time, end_time, waiting_time, turnaround_time;
	boolean is_completed;

	Process(int ID, int duration, int arrival_time) {
		this.ID = ID;
		this.duration = duration;
		this.arrival_time = arrival_time;
	}

	public int getId() {
		return ID;
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getArrivalTime() {
		return arrival_time;
	}

	public void setArrivalTime(int arrival_time) {
		this.arrival_time = arrival_time;
	}

	public void run(int ID, int duration, int arrival_time) {
		if (current_time < arrival_time) {
			current_time = arrival_time;
			start_time = current_time;
			current_time = current_time + duration;
			end_time = current_time;
			waiting_time = start_time - arrival_time;
			turnaround_time = end_time - arrival_time;
			is_completed = true;
		}
	}
}