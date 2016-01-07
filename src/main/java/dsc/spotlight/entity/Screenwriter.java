

/* First created by JCasGen Thu Jan 07 22:08:11 CET 2016 */
package dsc.spotlight.entity;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Jan 07 22:08:11 CET 2016
 * XML source: /Users/guilherme/code/617/spotlight/desc/types/PersonTypeSystem.xml
 * @generated */
public class Screenwriter extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Screenwriter.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Screenwriter() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Screenwriter(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Screenwriter(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Screenwriter(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: name

  /** getter for name - gets 
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (Screenwriter_Type.featOkTst && ((Screenwriter_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Screenwriter");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Screenwriter_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Screenwriter_Type.featOkTst && ((Screenwriter_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Screenwriter");
    jcasType.ll_cas.ll_setStringValue(addr, ((Screenwriter_Type)jcasType).casFeatCode_name, v);}    
  }

    