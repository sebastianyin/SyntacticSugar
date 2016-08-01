package frontend;

public interface IEditor {

	public void changeImage(String pathToImage);

	public void setAttribute(IAttribute atr);
	
	public void success();
	
	public void cancel();

}
