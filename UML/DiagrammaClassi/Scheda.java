/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package com.oggetti.Model1;

import java.util.*;
import java.time.*;



// ----------- << imports@AAAAAAGGu6gxiUljGHQ= >>
// ----------- >>

// ----------- << class.annotations@AAAAAAGGu6gxiUljGHQ= >>
// ----------- >>
public class Scheda {
    // ----------- << attribute.annotations@AAAAAAGHeseUyVlPQT4= >>
    // ----------- >>
    private LocalDate DataInizio;

    // ----------- << attribute.annotations@AAAAAAGHesf1DFpApwo= >>
    // ----------- >>
    private LocalDate DataFine;

    // ----------- << attribute.annotations@AAAAAAGHesg6x1sTI4U= >>
    // ----------- >>
    private String Obiettivo;

    // ----------- << attribute.annotations@AAAAAAGHesRi5UpfaqI= >>
    // ----------- >>
    private Set<Workout>  = new HashSet<>();

    // ----------- << attribute.annotations@AAAAAAGGu9yzEldfzAs= >>
    // ----------- >>
    private Set<Workout>  = new HashSet<>();

    public LocalDate getDataInizio() {
        return DataInizio;
    }

    public LocalDate getDataFine() {
        return DataFine;
    }

    public String getObiettivo() {
        return Obiettivo;
    }

    public Set<Workout> get() {
        return ;
    }

    public Set<Workout> get() {
        return ;
    }

    public void setDataInizio(LocalDate DataInizio) {
        this.DataInizio = DataInizio;
    }

    public void setDataFine(LocalDate DataFine) {
        this.DataFine = DataFine;
    }

    public void setObiettivo(String Obiettivo) {
        this.Obiettivo = Obiettivo;
    }

    public void link(Workout _) {
        if (_ != null) {
            _.unlink();
            _.set(this);
            get().add(_);
        }
    }

    public void link(Workout _) {
        if (_ != null) {
    		_.get().add(this);
            get().add(_);
        }
    }

    public void unlink(Workout _) {
        if (_ != null) {
            _.set(null);
            get().remove(_);
        }
    }

    public void unlink(Workout _, Iterator<Workout> it) {
        if (_ != null) {
            _.set(null);
            it.remove();
        }
    }

    public void unlink(Workout _) {
        if (_ != null) {
            _.get().remove(this);
            get().remove(_);
        }
    };

    public void unlink(Workout _, Iterator<Workout> it) {
        if (_ != null) {
            _.get().remove(this);
            it.remove();
        }
    }

    // ----------- << method.annotations@AAAAAAGHesh6u1uMRMI= >>
    // ----------- >>
    public void AddWorkout() {
    // ----------- << method.body@AAAAAAGHesh6u1uMRMI= >>
    // ----------- >>
    }
    // ----------- << method.annotations@AAAAAAGHesisxlxTFY0= >>
    // ----------- >>
    public void DeleteWorkout() {
    // ----------- << method.body@AAAAAAGHesisxlxTFY0= >>
    // ----------- >>
    }
// ----------- << class.extras@AAAAAAGGu6gxiUljGHQ= >>
// ----------- >>
}