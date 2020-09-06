package com.github.dabasan.mjgl.gl.scene.light;

import com.github.dabasan.ejml_3dtools.Vector;
import com.github.dabasan.mjgl.gl.Color;
import com.github.dabasan.mjgl.gl.IUpdatable;
import com.github.dabasan.mjgl.gl.scene.Node;
import com.github.dabasan.mjgl.gl.shader.ShaderProgram;

/**
 * Light
 * 
 * @author Daba
 *
 */
public class ParallelLight extends Node implements IUpdatable {
	private Vector target;
	private Color colorAmbient;
	private Color colorDiffuse;
	private Color colorSpecular;

	public ParallelLight() {
		this.setPosition(new Vector(1000.0, 1000.0, 1000.0));

		target = new Vector(0.0, 0.0, 0.0);
		colorAmbient = Color.WHITE.scale(0.6f);
		colorDiffuse = Color.WHITE.scale(0.3f);
		colorSpecular = Color.WHITE.scale(0.1f);
	}

	public Vector getTarget() {
		return target;
	}
	public Color getColorAmbient() {
		return colorAmbient;
	}
	public Color getColorDiffuse() {
		return colorDiffuse;
	}
	public Color getColorSpecular() {
		return colorSpecular;
	}

	public void setTarget(Vector target) {
		this.target = target;
	}
	public void setColorAmbient(Color colorAmbient) {
		this.colorAmbient = colorAmbient;
	}
	public void setColorDiffuse(Color colorDiffuse) {
		this.colorDiffuse = colorDiffuse;
	}
	public void setColorSpecular(Color colorSpecular) {
		this.colorSpecular = colorSpecular;
	}

	@Override
	public void update(ShaderProgram program, int index) {
		program.enable();
		if (index < 0) {
			program.setUniform("light.position", this.getPosition());
			program.setUniform("light.target", target);
			program.setUniform("light.colorAmbient", colorAmbient);
			program.setUniform("light.colorDiffuse", colorDiffuse);
			program.setUniform("light.colorSpecular", colorSpecular);
		} else {
			String strArray = "lights" + "[" + index + "]";

			program.setUniform(strArray + ".position", this.getPosition());
			program.setUniform(strArray + ".target", target);
			program.setUniform(strArray + ".colorAmbient", colorAmbient);
			program.setUniform(strArray + ".colorDiffuse", colorDiffuse);
			program.setUniform(strArray + ".colorSpecular", colorSpecular);
		}
	}
}
