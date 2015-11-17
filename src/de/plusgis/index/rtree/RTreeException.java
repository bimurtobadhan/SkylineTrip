/* 
 *
 * webEIEL
 * Sistema de Información Xeográfica da Deputación de A Coruña
 *
 * Este documento pode consultarse en galego nesta URL:
 *    http://www.dicoruna.es/webeiel/giseiel/licencia-giseiel_gl.do
 *
 * Este documento puede consultarse en castellano en esta URL:
 *    http://www.dicoruna.es/webeiel/giseiel/licencia-giseiel_es.do
 *
 * Copyright (C) 2008-2011 Deputación de A Coruña
 *
 * This software was developed by the Databases Laboratory of 
 * the University of A Coruña (http://lbd.udc.es). The authors of 
 * the software are enumerated in a file named AUTHORS.txt located 
 * on the root of the project.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,USA.
 *
 * For more information, contact:
 *
 *   Pedro A. González Pérez (pedro.gonzalez@dicoruna.es)
 *   Asistencia Técnica A Municipios
 *   Deputación Provincial de A Coruña
 *   Avda. Alférez Provisional, s/n
 *   15008 A Coruña
 *   Spain
 *
 *   Miguel R. Luaces (luaces@udc.es)
 *   Databases Laboratory
 *   Facultade de Informática
 *   Universidade da Coruña
 *   15071 A Coruña
 *   Spain
 *
 */
//RTree implementation.
//Copyright (C) 2002-2004 Wolfgang Baer - WBaer@gmx.de
//
//This library is free software; you can redistribute it and/or
//modify it under the terms of the GNU Lesser General Public
//License as published by the Free Software Foundation; either
//version 2.1 of the License, or (at your option) any later version.
//
//This library is distributed in the hope that it will be useful,
//but WITHOUT ANY WARRANTY; without even the implied warranty of
//MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
//Lesser General Public License for more details.
//
//You should have received a copy of the GNU Lesser General Public
//License along with this library; if not, write to the Free Software
//Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

package de.plusgis.index.rtree;

/**
 * <p>
 * The common exception thrown by problems during the rtree methods.
 * Thats the exception a user of the package gets if errors occur * 
 * </p>
 * @author Wolfgang Baer - WBaer@gmx.de
 */
public class RTreeException extends Exception {

	/**
	 * Constructor for RTreeException.
	 */
	public RTreeException() {
		super();
	}

	/**
	 * Constructor for RTreeException.
	 * @param message
	 */
	public RTreeException(String message) {
		super(message);
	}
	

}
