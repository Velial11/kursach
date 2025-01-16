package confluenceGenerator

class WrapperBaseConfl {
  static String getXML(String storage) {
    return """
      <ac:layout>
      ${storage.toString()}
      </ac:layout>
    """.toString
  }
}