package anotherpackage;
import mypackage.Calculate;
class PackageDemo
{
	public static void main(String ags[]){
			Calculate c=new Calculate();
			System.out.print(c.volume(3f,4f,4));
		//System.out.println(c.mul(3,4));//非法
		//System.out.println(c.add(5,6)); //非法
		}
}
