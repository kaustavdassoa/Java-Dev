package com.example.rssfeedtofile.config;

import com.rometools.rome.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.feed.dsl.Feed;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.AbstractPayloadTransformer;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.util.Date;

@Configuration
public class RSSFeedConfig {

    @Value(value = "http://feeds.bbci.co.uk/news/world/rss.xml")
    private Resource feedResource;


    @Value(value = "newsFeed.cvs")
    private String newsFeed;

    @Bean
    public IntegrationFlow feedFlow() {
        return IntegrationFlows
                .from(Feed.inboundAdapter(this.feedResource, "news"), e -> e.poller(p -> p.fixedDelay(5000)))
                .transform(extractLinkFromFeed())
                .handle(targetDirectory())
                .get();
    }


    @Bean
    public AbstractPayloadTransformer<SyndEntry, String> extractLinkFromFeed() {
        return new AbstractPayloadTransformer<SyndEntry, String>() {
            @Override
            protected String transformPayload(SyndEntry payload) {
                return new Date().toGMTString() +","+ payload.getTitle() + "," + payload.getPublishedDate() + "," + payload.getLink();
            }

        };

    }

    @Bean
    public MessageHandler targetDirectory() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("rss-feed"));

        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAutoCreateDirectory(true);
        handler.setFileNameGenerator(message -> newsFeed);
        // handler.setCharset("UTF-8");
        handler.setAppendNewLine(true);
        handler.setExpectReply(false);
        return handler;
    }





}
