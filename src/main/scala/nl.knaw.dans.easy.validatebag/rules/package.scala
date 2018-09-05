/**
 * Copyright (C) 2018 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.easy.validatebag

import nl.knaw.dans.easy.validatebag.rules.bagit.trace

import scala.util.Try

package object rules {
  // Relies on there being only one element with the specified name
  def getBagInfoTxtValue(t: TargetBag, element: String): Try[Option[String]] = {
    trace(t, element)
    t.tryBag.map { bag =>
      Option(bag.getMetadata.get(element))
        .flatMap {
          case list if list.isEmpty => None
          case list => Some(list.get(0))
        }
    }
  }
}