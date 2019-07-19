package ChapterFour;


interface Name {
	default String getName() {
		return getClass().getName() + "_" + hashCode();
	}
	String getClassName();
	String show();
}

interface Person {
	default String getName() {
		return "Java_" + hashCode();
	}
	String show();
}

public class InterfaceDefaultKeyWord implements Person, Name {
	@Override
	public String getName() {
		return getClass().getName();
	}
	
	@Override
	public String getClassName() {
		return Person.super.getName();
	}
	
	@Override
	public String show() {
		return "Interface";
	}
	
	public static void main(String[] args) {
		InterfaceDefaultKeyWord interfaceDefaultKeyWord = new InterfaceDefaultKeyWord();
		System.out.println(interfaceDefaultKeyWord.getName());
		System.out.println(interfaceDefaultKeyWord.getClassName());
		System.out.println(interfaceDefaultKeyWord.show());
	}

}
