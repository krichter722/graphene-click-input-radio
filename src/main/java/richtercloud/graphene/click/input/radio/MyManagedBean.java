package richtercloud.graphene.click.input.radio;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author richter
 */
@ManagedBean
public class MyManagedBean {
    private String myProperty;
    private String[] myProperties = new String[] {"a", "b", "c"};
    private String myPropertyPrime;
    private String[] myPropertiesPrime = new String[] {"aPrime", "bPrime", "cPrime"};
    private String myPropertyButtonPrime;
    private String[] myPropertiesButtonPrime = new String[] {"aPrime", "bPrime", "cPrime"};

    public MyManagedBean() {
    }

    public String getMyProperty() {
        return myProperty;
    }

    public void setMyProperty(String myProperty) {
        this.myProperty = myProperty;
    }

    public String[] getMyProperties() {
        return myProperties;
    }

    public void setMyProperties(String[] myProperties) {
        this.myProperties = myProperties;
    }

    public String getMyPropertyPrime() {
        return myPropertyPrime;
    }

    public void setMyPropertyPrime(String myPropertyPrime) {
        this.myPropertyPrime = myPropertyPrime;
    }

    public String[] getMyPropertiesPrime() {
        return myPropertiesPrime;
    }

    public void setMyPropertiesPrime(String[] myPropertiesPrime) {
        this.myPropertiesPrime = myPropertiesPrime;
    }

    public String getMyPropertyButtonPrime() {
        return myPropertyButtonPrime;
    }

    public void setMyPropertyButtonPrime(String myPropertyButtonPrime) {
        this.myPropertyButtonPrime = myPropertyButtonPrime;
    }

    public String[] getMyPropertiesButtonPrime() {
        return myPropertiesButtonPrime;
    }

    public void setMyPropertiesButtonPrime(String[] myPropertiesButtonPrime) {
        this.myPropertiesButtonPrime = myPropertiesButtonPrime;
    }
}
