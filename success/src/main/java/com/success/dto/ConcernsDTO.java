package com.success.dto;

/****************************************************************************************************************
* A data transfer object (DTO) used to represent concerns or issues within the system.
* This DTO encapsulates information related to specific concerns, allowing for easy transfer
* and communication between different components of the software.
* The ConcernsDTO class typically includes properties such as concern ID, description, categories, etc.
* associated components or modules, and any other relevant details or metadata.
* It serves as a structured representation of concerns, facilitating their tracking, analysis, and resolution
* throughout the development or maintenance process.
* ***************************************************************************************************************
*/
public class ConcernsDTO {

	private int guid;
	private String concernsName; // this refers to names of concerns such as hyper-active, adhd, difficulty in reading, etc.
	private String concernCategory; // this refers to whether it is an academic or behavioral category
	
	private String concernSubject; /* ****************************************************************
								   * this refers to if it is reading, writing or math for academics,
								   * or health problems, personal or family problems, cultural issues
							       * adjustment or developmental issues (e.g., “immaturity” or self-esteem
							       * issues), or general academic difficulties for behavior
							       */
	private String concernTopic;  /* ******************************************************************
								   * this refers to the specific areas within the subject like phonics,
								   * phonemic awareness, letter recognition, word recognition reading
								   * comprehension, vocabulary, fluency for reading, number-sense for math,
								   * grammar, word formation, etc. for writing or classroom incivilities 
								   * like in-attention, apathy, off-task talking, roaming, disruption, lying
								   * cheating, stealing, aggression or fighting, malicious mischief, defiance of
								   * authority
								   */
	private String concernDescription; // this describes the concernTopic further for the user to better understand it
	
	public int getConcernsId() {
		return guid;
	}
	public void setConcernsId(int concernsId) {
		this.guid = concernsId;
	}
	/**
	 * This is the general name, such as hyper-active, difficulty in reading, or math etc.
	 * @return a string of the possible common names for concerns
	 */
	public String getConcernsName() {
		return concernsName;
	}
	/**
	 * This sets a new general name for concerns commonly had, like hyper-active, difficulty in math, etc.
	 * @param concernsName is a string, so needs a string
	 */
	public void setConcernsName(String concernsName) {
		this.concernsName = concernsName;
	}
	/**
	 * This get's the description of the concern topic that refers to the specific areas within the subject 
	 * @return a string
	 */
	public String getConcernDescription() {
		return concernDescription;
	}
	/**
	 * This sets the description of the concern topic that refers to the specific areas within the subject 
	 * @param concernDescription is a string
	 */
	public void setConcernDescription(String concernDescription) {
		this.concernDescription = concernDescription;
	}
	/**
	 * Refers to whether it is an academic or behavioral category
	 * @return string of the category, either academic or behavioral.
	 */
	public String getConcernCategory() {
		return concernCategory;
	}
	/**
	 * Refers to whether it is an academic or behavioral category
	 * @param concernCategory is a string
	 */
	public void setConcernCategory(String concernCategory) {
		this.concernCategory = concernCategory;
	}
	
	/**
	 * This refers getting the concern's subject be it reading, writing or math for academics, or health problems, personal or family problems, etc. for behavior
	 * @return a concern subject as a string.
	 */
	public String getConcernSubject() {
		return concernSubject;
	}
	/**
	 * This refers to setting the concern subject if it is reading, writing or math for academics, or health problems, personal or family problems, etc. for behavior
	 * @param concernSubject is a string.
	 */
	public void setConcernSubject(String concernSubject) {
		this.concernSubject = concernSubject;
	}
	/**
	 * This refers to getting the specific areas within the subject like phonics for reading or classroom incivilities like in-attention, apathy, off-task talking, roaming
	 * @return
	 */
	public String getConcernTopic() {
		return concernTopic;
	}
	/**
	 * This refers to setting the specific areas within the subject like phonics for reading or classroom incivilities like in-attention, apathy, off-task talking, roaming
	 * @return a string of these concern topics
	 */
	public void setConcernTopic(String concernTopic) {
		this.concernTopic = concernTopic;
	}
	
	@Override
	public String toString() {
		return "ConcernsDTO [concernsId=" + guid + ", concernsName=" + concernsName + ", concernCategory="
				+ concernCategory + ", concernSubject=" + concernSubject + ", concernTopic=" + concernTopic
				+ ", concernDescription=" + concernDescription + "]";
	}
}
