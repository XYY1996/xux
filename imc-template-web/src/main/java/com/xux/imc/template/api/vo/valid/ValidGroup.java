package com.xux.imc.template.api.vo.valid;

import javax.validation.groups.Default;

public interface ValidGroup extends Default {
    interface Crud extends ValidGroup {

        interface Create extends Crud{

        }

        interface Update extends Crud{

        }

        interface Query extends Crud{

        }

        interface Delete extends Crud{

        }
    }
}