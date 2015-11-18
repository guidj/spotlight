

/* First created by JCasGen Wed Nov 18 11:13:36 CET 2015 */
package dsc.spotlight.entity;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Nov 18 13:33:32 CET 2015
 * XML source: /Users/guilherme/code/617/spotlight/descriptors/PersonDescriptor.xml
 * @generated */
public class Person extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Person.class);
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
  protected Person() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Person(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Person(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Person(JCas jcas, int begin, int end) {
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

  /** getter for name - gets Name of a person
   * @generated
   * @return value of the feature 
   */
  public String getName() {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Person");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Person_Type)jcasType).casFeatCode_name);}
    
  /** setter for name - sets Name of a person 
   * @generated
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (Person_Type.featOkTst && ((Person_Type)jcasType).casFeat_name == null)
      jcasType.jcas.throwFeatMissing("name", "dsc.spotlight.entity.Person");
    jcasType.ll_cas.ll_setStringValue(addr, ((Person_Type)jcasType).casFeatCode_name, v);}    
  }

    