package exception;

public class NotFindTheRowInDatabaseTable extends RuntimeException {

	public NotFindTheRowInDatabaseTable(String msg) {
		super(msg);
	}
}
