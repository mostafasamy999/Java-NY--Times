package com.samy.j_nytimes.data.response;

import java.util.List;

public class NewsResponse {
    private String copyright;
    private Response response;
    private String status;

    public NewsResponse(String copyright, Response response, String status) {
        this.copyright = copyright;
        this.response = response;
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public static class Byline {
        private Object organization;
        private String original;
        private List<Person> person;

        public String getOriginal() {
            return original;
        }

        public Byline(Object organization, String original, List<Person> person) {
            this.organization = organization;
            this.original = original;
            this.person = person;
        }
    }

    public static class Doc {
        private String _id;
        private String _abstract;
        private Byline byline;
        private String document_type;
        private Headline headline;
        private List<Keyword> keywords;
        private String lead_paragraph;
        private List<Multimedia> multimedia;
        private String news_desk;
        private String print_page;
        private String print_section;
        private String pub_date;
        private String section_name;
        private String snippet;
        private String source;
        private String subsection_name;
        private String type_of_material;
        private String uri;
        private String web_url;
        private int word_count;

        public Doc(String _id, String _abstract, Byline byline, String document_type, Headline headline,
                   List<Keyword> keywords, String lead_paragraph, List<Multimedia> multimedia, String news_desk,
                   String print_page, String print_section, String pub_date, String section_name, String snippet,
                   String source, String subsection_name, String type_of_material, String uri, String web_url, int word_count) {
            this._id = _id;
            this._abstract = _abstract;
            this.byline = byline;
            this.document_type = document_type;
            this.headline = headline;
            this.keywords = keywords;
            this.lead_paragraph = lead_paragraph;
            this.multimedia = multimedia;
            this.news_desk = news_desk;
            this.print_page = print_page;
            this.print_section = print_section;
            this.pub_date = pub_date;
            this.section_name = section_name;
            this.snippet = snippet;
            this.source = source;
            this.subsection_name = subsection_name;
            this.type_of_material = type_of_material;
            this.uri = uri;
            this.web_url = web_url;
            this.word_count = word_count;
        }

        public String get_id() {
            return _id;
        }

        public String get_abstract() {
            return _abstract;
        }

        public Byline getByline() {
            return byline;
        }

        public String getDocument_type() {
            return document_type;
        }

        public Headline getHeadline() {
            return headline;
        }

        public List<Keyword> getKeywords() {
            return keywords;
        }

        public String getLeadParagraph() {
            return lead_paragraph;
        }

        public List<Multimedia> getMultimedia() {
            return multimedia;
        }

        public String getNewsDesk() {
            return news_desk;
        }

        public String getPrint_page() {
            return print_page;
        }

        public String getPrint_section() {
            return print_section;
        }

        public String getPubDate() {
            return pub_date;
        }

        public String getSection_name() {
            return section_name;
        }

        public String getSnippet() {
            return snippet;
        }

        public String getSource() {
            return source;
        }

        public String getSubsection_name() {
            return subsection_name;
        }

        public String getType_of_material() {
            return type_of_material;
        }

        public String getUri() {
            return uri;
        }

        public String getWeb_url() {
            return web_url;
        }

        public int getWord_count() {
            return word_count;
        }
    }

    public static class Headline {
        private Object content_kicker;
        private String kicker;
        private String main;
        private Object name;
        private String print_headline;
        private Object seo;
        private Object sub;

        public String getMain() {
            return main;
        }

        public Headline(Object content_kicker, String kicker, String main, Object name, String print_headline, Object seo, Object sub) {
            this.content_kicker = content_kicker;
            this.kicker = kicker;
            this.main = main;
            this.name = name;
            this.print_headline = print_headline;
            this.seo = seo;
            this.sub = sub;
        }
    }

    public static class Keyword {
        private String major;
        private String name;
        private int rank;
        private String value;

        public Keyword(String major, String name, int rank, String value) {
            this.major = major;
            this.name = name;
            this.rank = rank;
            this.value = value;
        }
    }

    public static class Legacy {
        private String thumbnail;
        private int thumbnailheight;
        private int thumbnailwidth;
        private String wide;
        private int wideheight;
        private int widewidth;
        private String xlarge;
        private int xlargeheight;
        private int xlargewidth;

        public Legacy(String thumbnail, int thumbnailheight, int thumbnailwidth, String wide, int wideheight,
                      int widewidth, String xlarge, int xlargeheight, int xlargewidth) {
            this.thumbnail = thumbnail;
            this.thumbnailheight = thumbnailheight;
            this.thumbnailwidth = thumbnailwidth;
            this.wide = wide;
            this.wideheight = wideheight;
            this.widewidth = widewidth;
            this.xlarge = xlarge;
            this.xlargeheight = xlargeheight;
            this.xlargewidth = xlargewidth;
        }
    }

    public static class Meta {
        private int hits;
        private int offset;
        private int time;

        public Meta(int hits, int offset, int time) {
            this.hits = hits;
            this.offset = offset;
            this.time = time;
        }
    }

    public static class Multimedia {
        private Object caption;
        private Object credit;
        private String crop_name;
        private int height;
        private Legacy legacy;
        private int rank;
        private String subType;
        private String subtype;
        private String type;
        private String url;
        private int width;

        public String getUrl() {
            return url;
        }

        public Multimedia(Object caption, Object credit, String crop_name, int height, Legacy legacy, int rank,
                          String subType, String subtype, String type, String url, int width) {
            this.caption = caption;
            this.credit = credit;
            this.crop_name = crop_name;
            this.height = height;
            this.legacy = legacy;
            this.rank = rank;
            this.subType = subType;
            this.subtype = subtype;
            this.type = type;
            this.url = url;
            this.width = width;
        }
    }

    public static class Person {
        private String firstname;
        private String lastname;
        private String middlename;
        private String organization;
        private Object qualifier;
        private int rank;
        private String role;
        private Object title;

        public Person(String firstname, String lastname, String middlename, String organization, Object qualifier,
                      int rank, String role, Object title) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.middlename = middlename;
            this.organization = organization;
            this.qualifier = qualifier;
            this.rank = rank;
            this.role = role;
            this.title = title;
        }
    }

    public static class Response {
        private List<Doc> docs;
        private Meta meta;

        public List<Doc> getDocs() {
            return docs;
        }

        public Response(List<Doc> docs, Meta meta) {
            this.docs = docs;
            this.meta = meta;
        }
    }
}
