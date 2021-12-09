package MySql;

public class SqlMessage {

	private SqlMessageTypes type;
	private Object message;

	public SqlMessage(SqlMessageTypes type, Object message) {
		this.type = type;
		this.message = message;
	}

	public SqlMessageTypes getType() {
		return type;
	}

	public void setType(SqlMessageTypes type) {
		this.type = type;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return getType().toString() + " " + message;
	}
}
