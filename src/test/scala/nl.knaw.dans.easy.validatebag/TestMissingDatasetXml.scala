package nl.knaw.dans.easy.validatebag

import java.net.URI
import java.nio.file.Paths

import org.apache.commons.configuration.PropertiesConfiguration

import scala.util.{ Failure, Success }

object TestMissingDatasetXml extends App {

  val props = new PropertiesConfiguration(getClass.getResource("/debug-config/application.properties"))
  val lics = List(
    "http://creativecommons.org/publicdomain/zero/1.0",
    "http://creativecommons.org/licenses/by-nc/3.0",
    "http://creativecommons.org/licenses/by-nc-sa/3.0",
    "http://creativecommons.org/licenses/by/4.0",
    "http://creativecommons.org/licenses/by-sa/4.0/",
    "http://creativecommons.org/licenses/by-nc/4.0/",
    "http://creativecommons.org/licenses/by-nd/4.0/",
    "http://creativecommons.org/licenses/by-nc-nd/4.0/",
    "http://creativecommons.org/licenses/by-nc-sa/4.0/",
    "http://opensource.org/licenses/BSD-2-Clause",
    "http://opensource.org/licenses/BSD-3-Clause",
    "http://opensource.org/licenses/MIT",
    "http://www.apache.org/licenses/LICENSE-2.0",
    "http://www.cecill.info/licences/Licence_CeCILL_V2-en.html",
    "http://www.cecill.info/licences/Licence_CeCILL-B_V1-en.html",
    "http://www.gnu.org/licenses/gpl-3.0.en.html",
    "http://www.gnu.org/licenses/lgpl-3.0.txt",
    "http://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html",
    "http://www.mozilla.org/en-US/MPL/2.0/FAQ/",
    "http://www.ohwr.org/attachments/735/CERNOHLv1_1.txt",
    "http://www.ohwr.org/attachments/2388/cern_ohl_v_1_2.txt",
    "http://www.ohwr.org/projects/cernohl/wiki",
    "http://www.tapr.org/ohl.html",
    "http://www.tapr.org/TAPR_Open_Hardware_License_v1.0.txt",
  ).map(new URI(_))
  val configuration = new Configuration("my-version", props, lics)
  val app = new EasyValidateDansBagApp(configuration)

  val noDatasetXmlURI = getClass.getResource("/bags/metadata-no-dataset-xml").toURI
  val noDatasetXml = Paths.get(noDatasetXmlURI)

  app.validate(noDatasetXmlURI, InfoPackageType.BOTH) match {
    case Failure(e) => e.printStackTrace()
    case Success(msg) => println(msg)
  }
}
