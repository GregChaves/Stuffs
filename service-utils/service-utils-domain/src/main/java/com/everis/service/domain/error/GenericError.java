package com.everis.service.domain.error;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author gbritoch
 *
 */
public class GenericError {

	private Integer idError;

	public Integer getIdError() {
		return idError;
	}

	public void setIdError(Integer idError) {
		this.idError = idError;
	}

	public static class Builder {
		private Integer idError;

		public Builder idError(Integer idError) {
			this.idError = idError;
			return this;
		}

		public GenericError build() {
			GenericError errorDomain = new GenericError();
			errorDomain.idError = idError;
			return errorDomain;
		}
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}
}
