package com.tvlife.imageloader.cache.disc.impl;

import com.tvlife.imageloader.cache.disc.LimitedDiscCache;
import com.tvlife.imageloader.cache.disc.naming.FileNameGenerator;
import com.tvlife.imageloader.core.DefaultConfigurationFactory;
import com.tvlife.imageloader.utils.L;
import java.io.File;

public class TotalSizeLimitedDiscCache extends LimitedDiscCache {
    private static final int MIN_NORMAL_CACHE_SIZE = 2097152;
    private static final int MIN_NORMAL_CACHE_SIZE_IN_MB = 2;

    public TotalSizeLimitedDiscCache(File cacheDir, int maxCacheSize) {
        this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), maxCacheSize);
    }

    public TotalSizeLimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int maxCacheSize) {
        super(cacheDir, fileNameGenerator, maxCacheSize);
        if (maxCacheSize < 2097152) {
            L.w("You set too small disc cache size (less than %1$d Mb)", 2);
        }
    }

    /* access modifiers changed from: protected */
    public int getSize(File file) {
        return (int) file.length();
    }
}
