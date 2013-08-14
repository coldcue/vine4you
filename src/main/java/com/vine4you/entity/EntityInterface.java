/******************************************************************************
 * Copyright (c) 2013 by Andrew Szell (coldcue@gmail.com)                     *
 *                                                                            *
 * All rights reserved. No part of this code may be reproduced, distributed,  *
 * or transmitted in any form or by any means, including photocopying,        *
 * recording, or other electronic or mechanical methods, without the prior    *
 * written permission of the owner.                                           *
 ******************************************************************************/

package com.vine4you.entity;

import com.google.appengine.api.datastore.Entity;

/**
 * User: Andrew
 * Date: 8/14/13
 * Time: 5:10 PM
 */
public interface EntityInterface {
    public Entity generateEntity();
}
