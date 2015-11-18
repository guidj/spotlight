

/* First created by JCasGen Wed Nov 18 12:46:09 CET 2015 */
package dsc.spotlight.entity;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Nov 18 13:13:33 CET 2015
 * XML source: /Users/guilherme/code/617/spotlight/descriptors/PersonDescriptor.xml
 * @generated */
public class Cinematographer extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Cinematographer.class);
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
  protected Cinematographer() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Cinematographer(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Cinematographer(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Cinematographer(JCas jcas, int begin, int end) {
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
    if (Cinematographer_Type.featOkTst && ((Cinematographer_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Cinematographer");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Cinematographer_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Cinematographer_Type.featOkTst && ((Cinematographer_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Cinematographer");
    jcasType.ll_cas.ll_setStringValue(addr, ((Cinematographer_Type)jcasType).casFeatCode_name, v);}    
  }

    