package com.github.dabasan.mjgl.gl.scene.camera;

import java.util.List;

import com.github.dabasan.ejml_3dtools.Matrix;
import com.github.dabasan.mjgl.gl.scene.IUpdatable;
import com.github.dabasan.mjgl.gl.shader.ShaderProgram;

/**
 * Orthographic camera
 * 
 * @author Daba
 *
 */
public class OrthographicCamera extends Camera implements IUpdatable {
	private double size;

	public OrthographicCamera() {
		size = 10.0;
	}

	public void setSize(double size) {
		this.size = size;
	}
	public double getSize() {
		return size;
	}

	@Override
	public void update(List<ShaderProgram> programs) {
		Matrix viewTransformation = MatrixFunctions.getViewTransformationMatrix(this.getPosition(),
				this.getTarget(), this.getUp());
		Matrix projection = MatrixFunctions.getOrthogonalMatrix(-size, size, -size, size,
				this.getNear(), this.getFar());

		Matrix vp = projection.mult(viewTransformation);

		for (var program : programs) {
			program.enable();
			program.setUniform("camera.near", this.getNear());
			program.setUniform("camera.far", this.getFar());
			program.setUniform("camera.position", this.getPosition());
			program.setUniform("camera.target", this.getTarget());
			program.setUniform("camera.vp", vp, true);
		}
	}

}