/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package
    org.apache.openjpa.persistence.annotations.common.apps.annotApp.annotype;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.openjpa.persistence.jdbc.ElementJoinColumn;

@Entity
@Table(name = "ANNOTEST1")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ANNOCLS")
@DiscriminatorValue("ANNO1")
@SecondaryTables({ @SecondaryTable(name = "OTHER_ANNOTEST1",
    pkJoinColumns = @PrimaryKeyJoinColumn(name = "OTHER_PK",
        referencedColumnName = "PK")) })
public class AnnoTest1 {

    @Id
    @Column(name = "PK")
    private Long pk;

    @Version
    @Column(name = "ANNOVER")
    private int version;

    @Basic
    private int basic;

    @Transient
    private int trans;

    @Basic
    @Column(name = "OTHERVALUE", table = "OTHER_ANNOTEST1")
    private int otherTableBasic;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELFONEONE_PK", referencedColumnName = "PK")
    private AnnoTest1 selfOneOne;

    @OneToOne
    @PrimaryKeyJoinColumn
    private AnnoTest1 pkJoinSelfOneOne;

    @OneToOne
    @JoinColumns({
    @JoinColumn(name = "ONEONE_PK1", referencedColumnName = "PK1"),
    @JoinColumn(name = "ONEONE_PK2", referencedColumnName = "PK2") })
    private AnnoTest2 oneOne;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    @JoinColumn(name = "OTHERONE_PK1", referencedColumnName = "PK1",
        table = "OTHER_ANNOTEST1"),
    @JoinColumn(name = "OTHERONE_PK2", referencedColumnName = "PK2",
        table = "OTHER_ANNOTEST1") })
    private AnnoTest2 otherTableOneOne;

    @OneToOne(mappedBy = "inverseOneOne", fetch = FetchType.LAZY)
    private AnnoTest2 inverseOwnerOneOne;

    @Lob
    @Column(name = "BLOBVAL")
    private byte[] blob;

    @Basic
    @Lob
    @Column(name = "SERVAL")
    private Object serial;

    @Column(name = "CLOBVAL")
    @Lob
    private String clob;

    // un-annotated enum should be persisted by default
    @Column(name = "ENUMVAL")
    private InheritanceType enumeration;

    @Enumerated
    @Column(name = "ORD_ENUMVAL")
    private InheritanceType ordinalEnumeration;

    @Enumerated(EnumType.STRING)
    @Column(name = "STR_ENUMVAL")
    private InheritanceType stringEnumeration;

    @OneToMany
    @ElementJoinColumn(name = "ONEMANY_PK", referencedColumnName = "PK")
    private Set<AnnoTest2> oneMany = new HashSet();

    @OneToMany(mappedBy = "oneManyOwner")
    private Set<AnnoTest2> inverseOwnerOneMany = new HashSet();

    @ManyToMany
    @JoinTable(name = "ANNOTEST1_MANYMANY",
        joinColumns = @JoinColumn(name = "MANY_PK"),
        inverseJoinColumns = {
        @JoinColumn(name = "MANY_PK1", referencedColumnName = "PK1"),
        @JoinColumn(name = "MANY_PK2", referencedColumnName = "PK2") })
    private Set<AnnoTest2> manyMany = new HashSet();

    @ManyToMany(mappedBy = "manyMany")
    private Set<AnnoTest2> inverseOwnerManyMany = new HashSet();

    @MapKey
    @OneToMany
    private Map<Integer, Flat1> defaultMapKey = new HashMap();

    @MapKey(name = "basic")
    @OneToMany
    private Map<Integer, Flat1> namedMapKey = new HashMap();

    @MapKey(name = "basic")
    @OneToMany(mappedBy = "oneManyOwner")
    private Map<String, AnnoTest2> inverseOwnerMapKey = new HashMap();

    public AnnoTest1() {
    }

    public AnnoTest1(long pk) {
        this.pk = new Long(pk);
    }

//    public AnnoTest1(Long pk) {
//        this.pk = pk;
//    }

    public void setPk(Long val) {
        pk = val;
    }

    public Long getPk() {
        return pk;
    }

    public int getVersion() {
        return version;
    }

    public void setBasic(int i) {
        basic = i;
    }

    public int getBasic() {
        return basic;
    }

    public void setTransient(int i) {
        trans = i;
    }

    public int getTransient() {
        return trans;
    }

    public void setOtherTableBasic(int i) {
        otherTableBasic = i;
    }

    public int getOtherTableBasic() {
        return otherTableBasic;
    }

    public void setSelfOneOne(AnnoTest1 other) {
        selfOneOne = other;
    }

    public AnnoTest1 getSelfOneOne() {
        return selfOneOne;
    }

    public void setPKJoinSelfOneOne(AnnoTest1 other) {
        pkJoinSelfOneOne = other;
    }

    public AnnoTest1 getPKJoinSelfOneOne() {
        return pkJoinSelfOneOne;
    }

    public void setOneOne(AnnoTest2 other) {
        oneOne = other;
    }

    public AnnoTest2 getOneOne() {
        return oneOne;
    }

    public void setOtherTableOneOne(AnnoTest2 other) {
        otherTableOneOne = other;
    }

    public AnnoTest2 getOtherTableOneOne() {
        return otherTableOneOne;
    }

    public void setInverseOwnerOneOne(AnnoTest2 other) {
        inverseOwnerOneOne = other;
    }

    public AnnoTest2 getInverseOwnerOneOne() {
        return inverseOwnerOneOne;
    }

    public void setBlob(byte[] bytes) {
        blob = bytes;
    }

    public byte[] getBlob() {
        return blob;
    }

    public void setSerialized(Object o) {
        serial = o;
    }

    public Object getSerialized() {
        return serial;
    }

    public void setClob(String s) {
        clob = s;
    }

    public String getClob() {
        return clob;
    }

    public InheritanceType getEnumeration() {
        return enumeration;
    }

    public void setEnumeration(InheritanceType val) {
        enumeration = val;
    }

    public InheritanceType getOrdinalEnumeration() {
        return ordinalEnumeration;
    }

    public void setOrdinalEnumeration(InheritanceType val) {
        ordinalEnumeration = val;
    }

    public InheritanceType getStringEnumeration() {
        return stringEnumeration;
    }

    public void setStringEnumeration(InheritanceType val) {
        stringEnumeration = val;
    }

    public Set<AnnoTest2> getOneMany() {
        return oneMany;
    }

    public Set<AnnoTest2> getInverseOwnerOneMany() {
        return inverseOwnerOneMany;
    }

    public Set<AnnoTest2> getManyMany() {
        return manyMany;
    }

    public Set<AnnoTest2> getInverseOwnerManyMany() {
        return inverseOwnerManyMany;
    }

    public Map<Integer, Flat1> getDefaultMapKey() {
        return this.defaultMapKey;
    }

    public void setDefaultMapKey(Map<Integer, Flat1> defaultMapKey) {
        this.defaultMapKey = defaultMapKey;
    }

    public Map<Integer, Flat1> getNamedMapKey() {
        return this.namedMapKey;
    }

    public void setNamedMapKey(Map<Integer, Flat1> namedMapKey) {
        this.namedMapKey = namedMapKey;
    }

    public Map<String, AnnoTest2> getInverseOwnerMapKey() {
        return this.inverseOwnerMapKey;
    }

    public void setInverseOwnerMapKey(
        Map<String, AnnoTest2> inverseOwnerMapKey) {
        this.inverseOwnerMapKey = inverseOwnerMapKey;
    }
}

