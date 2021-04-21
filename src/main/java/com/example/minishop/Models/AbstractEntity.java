package com.example.minishop.Models;


import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public interface ValidationMinimal {
	};

	public interface ValidationActive {
	};

}
