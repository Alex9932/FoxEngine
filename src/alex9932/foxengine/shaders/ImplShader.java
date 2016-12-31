package alex9932.foxengine.shaders;

public class ImplShader extends ShaderProgram{

	private static final String VERTEX_SHADER = "./res/shaders/shader.vs";
	private static final String FRABMENT_SHADER = "./res/shaders/shader.fs";
	
	public ImplShader() {
		super(VERTEX_SHADER, FRABMENT_SHADER);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}	
}