package toy.shopping.pay.member.model.exception;

public class MemberException extends RuntimeException{
	public MemberException() {}
	public MemberException(String emg) {
		super(emg);
	}
}
