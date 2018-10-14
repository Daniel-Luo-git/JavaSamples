package proxy;

public class UserServiceImpl implements UserService {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		System.out.println("------getName------");
		return "Tom";
	}

	@Override
	public int getAge() {
		// TODO Auto-generated method stub
		System.out.println("------getAge------");
		return 15;
	}

}
